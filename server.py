from model import RecommendModel as rm
from data_processor import data_processor as dp
import pandas as pd
import sqlite3
import surprise
import random
from flask import request, jsonify
import flask
from wtforms import Form, FloatField, validators,StringField
import sys
from flask import Flask, render_template, request
import sys
import numpy as np
app = Flask(__name__)
@app.route('/', methods=['GET'])
def api_id():
	DBpath = "yelp.db"
	latitude =  request.args.get('latitude',type = float)
	longtitude= request.args.get('longtitude',type = float)
	user_id = request.args.get('user_id',type=str)
	posWindow=[latitude-0.5,latitude+0.5,longtitude-3,longtitude+3]
	boolLocate = True
	topN=10
	data = dp(user_id,DBpath,posWindow,boolLocate)
	d,data_to_user,new_user = data.make_df()
	user_data = []
	model = rm(user_data,user_id,DBpath,posWindow,boolLocate,10)
	dat = model.content()
	if new_user:
		data_to_user[10]=1
		return data_to_user
		
	else:
		dat[10]=0
		return dat
app.run()
