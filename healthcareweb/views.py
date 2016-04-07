from django.http import HttpResponse
from django.shortcuts import render
from django.core import serializers
#from healthcareweb.db.mysqlconn import getConnection
#from healthcareweb.models.doctor import Doctor
from healthcareweb.models import Doctor
from django.views.decorators.csrf import csrf_exempt,csrf_protect
from django.http import Http404,HttpRequest,HttpResponse,HttpResponseRedirect
from django.views.decorators.http import require_http_methods
from django.contrib.auth.models import User
from django.contrib.auth import authenticate, login, logout
from django.shortcuts import redirect
import json
from django.db import connection

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
	addrLine1 = request.POST.get("addrLine1");
	addrLine2 = request.POST.get("addrLine2");
	postCode = request.POST.get("postCode");
	city = request.POST.get("city");
	mobile = request.POST.get("mobile");
	email = request.POST.get("email");
	website = request.POST.get("website");
	doctor = Doctor.objects.create(title=title,firstName=firstName,lastName=lastName,specialization=specialization,
		addrLine1=addrLine1,addrLine2=addrLine2, postCode=postCode,city=city,mobile=mobile,email=email,website=website)

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
	autoCompleteResults = []

	fnameSQL = 'SELECT  firstName FROM healthcareweb_doctor WHERE firstName LIKE %s'
	cursor.execute(fnameSQL,args)
	firstNames =  cursor.fetchall()
	#Doctor.objects.raw('SELECT  * FROM healthcareweb_doctor WHERE firstName LIKE \'%s\'' % queryStr)
	for fn in firstNames:
		print(fn)
		autoCompleteResults.append(fn[0])

	#lastNames =  Doctor.objects.raw('SELECT  * FROM healthcareweb_doctor WHERE lastName LIKE \'%s\'' % queryStr)
	lnameSQL = 'SELECT  lastName FROM healthcareweb_doctor WHERE lastName LIKE %s'
	cursor.execute(lnameSQL,args)
	lastNames =  cursor.fetchall()
	for ln in lastNames:
		print(ln)
		autoCompleteResults.append(ln[0])

	#specializations =  Doctor.objects.raw('SELECT  * FROM healthcareweb_doctor WHERE specialization LIKE \'%s\'' % queryStr)
	specializationSQL = 'SELECT  specialization FROM healthcareweb_doctor WHERE specialization LIKE %s'
	cursor.execute(specializationSQL,args)
	specializations =  cursor.fetchall()
	for s in specializations:
		print(s)
		autoCompleteResults.append(s[0])
	
	#citis =  Doctor.objects.raw('SELECT  * FROM healthcareweb_doctor WHERE city LIKE \'%s\'' % queryStr)
	citySQL = 'SELECT  city FROM healthcareweb_doctor WHERE city LIKE %s'
	cursor.execute(citySQL,args)
	citis =  cursor.fetchall()
	for c in citis:
		print(c)
		autoCompleteResults.append(c[0])

	#data = serializers.serialize('json', [list(firstNames)])
	return HttpResponse(json.dumps(autoCompleteResults))