# -*- coding: utf-8 -*-
# Generated by Django 1.9.4 on 2016-04-15 21:51
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('healthcareweb', '0002_specialization'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='doctor',
            name='specialization',
        ),
        migrations.AddField(
            model_name='specialization',
            name='doctors',
            field=models.ManyToManyField(to='healthcareweb.Doctor'),
        ),
    ]
