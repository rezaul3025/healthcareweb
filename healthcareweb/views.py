from django.http import HttpResponse
from django.shortcuts import render
from healthcareweb.db.mysqlconn import getConnection

def index(request):
	#conn = getConnection()
	return render(request, 'index.html')

def signup(request):
	return render(request, 'signup.html')