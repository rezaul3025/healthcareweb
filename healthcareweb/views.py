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
import json

def index(request):
	#conn = getConnection()
	return render(request, 'index.html')

def signup(request):
	return render(request, 'signup.html')

@require_http_methods(["POST"])
@csrf_exempt
def dosignup(request):
	title = request.POST['title']
	print(request)
	print(request.method == 'POST')
	print("tiii test")
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