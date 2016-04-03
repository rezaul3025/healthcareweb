from django.http import HttpResponse
from django.shortcuts import render
from django.core import serializers
from healthcareweb.db.mysqlconn import getConnection
#from healthcareweb.models.doctor import Doctor
from healthcareweb.models import Doctor
from django.views.decorators.csrf import csrf_exempt,csrf_protect
from django.http import Http404,HttpRequest,HttpResponse,HttpResponseRedirect
from django.views.decorators.http import require_http_methods
import json

def index(request):
	#conn = getConnection()
	return render(request, 'index.html')

def signup(request):
	return render(request, 'signup.html')

@require_http_methods(["POST"])
@csrf_exempt
def dosignup(request):
	title = request.POST["title"];
	firstName = request.POST["firstName"];
	lastName = request.POST["lastName"];
	specialization = request.POST["specialization"];
	addrLine1 = request.POST["addrLine1"];
	addrLine2 = request.POST["addrLine2"];
	postCode = request.POST["postCode"];
	city = request.POST["city"];
	mobile = request.POST["mobile"];
	email = request.POST["email"];
	website = request.POST["website"];
	doctor = Doctor.objects.create(title=title,firstName=firstName,lastName=lastName,specialization=specialization,
		addrLine1=addrLine1,addrLine2=addrLine2, postCode=postCode,city=city,mobile=mobile,email=email,website=website)
	#print(d.title)
	data = serializers.serialize('json', [doctor])
	#json.dumps(data, content_type="application/json")
	#data = serializers.serialize('json', self.get_queryset())
	#return HttpResponse(data, mimetype="application/json")
	return HttpResponse(data) 