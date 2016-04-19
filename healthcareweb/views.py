from django.http import HttpResponse
from django.shortcuts import render, RequestContext,render_to_response
from django.core import serializers
#from healthcareweb.db.mysqlconn import getConnection
#from healthcareweb.models.doctor import Doctor
from healthcareweb.models import Doctor
from healthcareweb.models import Specialization
from django.views.decorators.csrf import csrf_exempt,csrf_protect
from django.http import Http404,HttpRequest,HttpResponse,HttpResponseRedirect
from django.core.urlresolvers import reverse
from django.views.decorators.http import require_http_methods
from django.contrib.auth.models import User
from django.contrib.auth import authenticate, login, logout
from django.shortcuts import redirect
import json
from django.db import connection
from django.db.models import Q
from django.core.paginator import Paginator, EmptyPage, PageNotAnInteger



def index(request):
	#conn = getConnection()
	return render(request, 'index.html')

def signup(request):
	return render(request, 'signup.html')

def logininit(request):
	return render(request, 'login.html')	

@require_http_methods(["POST"])
def dologin(request):
    username = request.POST['username']
    password = request.POST['password']
    user = authenticate(username=username, password=password)
    if user is not None:
        if user.is_active:
            login(request, user)
            # Redirect to a success page.
            return  redirect('/') #render(request, 'index.html')
        else:
            #Return a 'disabled account' error message
            return  redirect('/login/') #render(request, 'login.html')
    else:
        #Return an 'invalid login' error message
        return  redirect('/login/') #render(request, 'login.html')  
		
def dologout(request):
	logout(request)
	# Redirect to a success page.
	return  redirect('/') #render(request, 'index.html')

@require_http_methods(["POST"])
@csrf_exempt
def dosignup(request):
	title = request.POST['title']
	firstName = request.POST.get("firstName");
	print(firstName)
	lastName = request.POST.get("lastName");
	specialization = request.POST.get("specialization");
	print(specialization)
	addrLine1 = request.POST.get("addrLine1");
	addrLine2 = request.POST.get("addrLine2");
	postCode = request.POST.get("postCode");
	city = request.POST.get("city");
	mobile = request.POST.get("mobile");
	email = request.POST.get("email");
	website = request.POST.get("website");
	doctor = Doctor.objects.create(title=title,firstName=firstName,lastName=lastName,
		addrLine1=addrLine1,addrLine2=addrLine2, postCode=postCode,city=city,mobile=mobile,email=email,website=website)
	
	specialization = specialization.split(",")
	print(specialization)
	specializations = Specialization.objects.filter(name__in=specialization)
	print(len(specializations))
	for sp in specializations:
		sp.doctors.add(doctor)
		sp.save()
		print(sp.name)
	user = User.objects.create_user(username=request.POST['userName'], password = request.POST['password'], email = email, first_name=firstName, last_name = lastName)
	#user.save()
	#print(d.title)
	data = serializers.serialize('json', [doctor])
	#json.dumps(data, content_type="application/json")
	#data = serializers.serialize('json', self.get_queryset())
	#return HttpResponse(data, mimetype="application/json")
	return HttpResponse(data) 

@require_http_methods(["GET"])
def getSearchAutocomplete(request):
	queryStr = request.GET['queryStr']
	cursor = connection.cursor()

	queryStr = queryStr.replace('\'', '').replace('%', '')
	args = [queryStr+'%']
	print(queryStr)
	autoCompleteResults = set()

	fnameSQL = 'SELECT  firstName FROM healthcareweb_doctor WHERE firstName LIKE %s'
	cursor.execute(fnameSQL,args)
	firstNames =  cursor.fetchall()
	#Doctor.objects.raw('SELECT  * FROM healthcareweb_doctor WHERE firstName LIKE \'%s\'' % queryStr)
	for fn in firstNames:
		print(fn)
		autoCompleteResults.add(fn[0])

	#lastNames =  Doctor.objects.raw('SELECT  * FROM healthcareweb_doctor WHERE lastName LIKE \'%s\'' % queryStr)
	lnameSQL = 'SELECT  lastName FROM healthcareweb_doctor WHERE lastName LIKE %s'
	cursor.execute(lnameSQL,args)
	lastNames =  cursor.fetchall()
	for ln in lastNames:
		print(ln)
		autoCompleteResults.add(ln[0])

	#specializations =  Doctor.objects.raw('SELECT  * FROM healthcareweb_doctor WHERE specialization LIKE \'%s\'' % queryStr)
	specializationSQL = 'SELECT  name FROM healthcareweb_specialization WHERE name LIKE %s'
	cursor.execute(specializationSQL,args)
	specializations =  cursor.fetchall()
	for s in specializations:
		print(s)
		autoCompleteResults.add(s[0])
	
	#citis =  Doctor.objects.raw('SELECT  * FROM healthcareweb_doctor WHERE city LIKE \'%s\'' % queryStr)
	citySQL = 'SELECT  city FROM healthcareweb_doctor WHERE city LIKE %s'
	cursor.execute(citySQL,args)
	citis =  cursor.fetchall()
	for c in citis:
		print(c)
		autoCompleteResults.add(c[0])

	#data = serializers.serialize('json', [list(firstNames)])
	return HttpResponse(json.dumps(list(autoCompleteResults)), content_type="application/json")

@require_http_methods(["GET"])
def getAllSpecializations(request):
	queryStr = request.GET['queryStr']
	allSpecializationsOb = Specialization.objects.filter(Q(name__icontains=queryStr))
	allSpecializations = []
	for sp in allSpecializationsOb:
		allSpecializations.append(sp.name)
	return HttpResponse(json.dumps(allSpecializations), content_type="application/json")

@require_http_methods(["GET"])
def getAllCities(request):
	queryStr = request.GET['queryStr']
	doctorOb = Doctor.objects.filter(Q(city__icontains=queryStr))
	cities = set()
	for d in doctorOb:
		cities.add(d.city)
	return HttpResponse(json.dumps(list(cities)), content_type="application/json")


def simpleSearch(request):
	queryStr = request.GET['queryStr']
	try:
		doctor_list = Doctor.objects.filter(Q(firstName__startswith=queryStr) | Q(lastName__startswith=queryStr) |
			Q(city__startswith=queryStr))
		paginator = Paginator(doctor_list, 5) # Show 2 contacts per page
		page = request.GET.get('page')
		doctors = paginator.page(page)
	except PageNotAnInteger:
		# If page is not an integer, deliver first page.
		doctors = paginator.page(1)
	except EmptyPage:
		# If page is out of range (e.g. 9999), deliver last page of results.
		doctors = paginator.page(paginator.num_pages)
	except Exception as ex:
		print(ex)
	
	#data = serializers.serialize('json', doctors)
	print(doctors)
	
	return render(request, 'index.html', {'doctors': doctors, 'queryStr':queryStr}) #render_to_response('index.html',doctor_l, context) # #

	#return HttpResponse(data, content_type="application/json")
	
@require_http_methods(["GET"])	
def advanceSearch(request):
	specializationsStr = request.GET['specializationsStr']
	cityStr = request.GET['cityStr']
	try:
		#doctor_list = Doctor.objects.filter(Q(specialization__icontains=queryStr) | 
		#	Q(firstName__startswith=queryStr) | Q(lastName__startswith=queryStr) |
		#	Q(city__startswith=queryStr))
		doctor_list = []
		specializations = Specialization.objects.filter(name__in=specializationsStr.split(","))
		for sp in specializations:
			doctor_list.append(sp.doctors.filter(city=cityStr))	
		
		print(doctor_list)
		paginator = Paginator(doctor_list, 5) # Show 2 contacts per page
		page = request.GET.get('page')
		doctors = paginator.page(page)
	except PageNotAnInteger:
		# If page is not an integer, deliver first page.
		doctors = paginator.page(1)
	except EmptyPage:
		# If page is out of range (e.g. 9999), deliver last page of results.
		doctors = paginator.page(paginator.num_pages)
	except Exception as ex:
		print(ex)
	print(doctors)
	
	#data = serializers.serialize('json', doctors)
	
	
	return render(request, 'index.html', {'doctors': doctors, 'specializationsStr':specializationsStr}) #render_to_response('index.html',doctor_l, context) # #

	#return HttpResponse(data, content_type="application/json")

