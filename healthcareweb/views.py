from django.http import HttpResponse
from django.shortcuts import render
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
	d = Doctor.objects.create(title="last name")

	return HttpResponse(json.dumps(d), content_type="application/json") 

