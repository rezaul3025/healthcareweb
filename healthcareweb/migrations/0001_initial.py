# -*- coding: utf-8 -*-
# Generated by Django 1.9.4 on 2016-04-03 17:04
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Doctor',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('title', models.CharField(max_length=10)),
                ('firstName', models.CharField(max_length=55)),
                ('lastName', models.CharField(max_length=55)),
                ('specialization', models.TextField()),
                ('addrLine1', models.CharField(max_length=100)),
                ('addrLine2', models.CharField(max_length=100, verbose_name='')),
                ('postCode', models.CharField(max_length=20, verbose_name='')),
                ('city', models.CharField(max_length=100)),
                ('mobile', models.CharField(max_length=20)),
                ('email', models.CharField(max_length=120, verbose_name='')),
                ('website', models.TextField()),
            ],
        ),
    ]