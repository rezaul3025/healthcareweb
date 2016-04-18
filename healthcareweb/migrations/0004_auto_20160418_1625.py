# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('healthcareweb', '0003_auto_20160415_2351'),
    ]

    operations = [
        migrations.CreateModel(
            name='Rating',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('points', models.IntegerField(verbose_name=0)),
                ('userEmail', models.CharField(max_length=120)),
                ('comments', models.TextField(verbose_name=b'')),
                ('doctor', models.ManyToManyField(related_name='rating', to='healthcareweb.Doctor')),
            ],
        ),
        migrations.AlterField(
            model_name='specialization',
            name='doctors',
            field=models.ManyToManyField(related_name='specializations', to='healthcareweb.Doctor'),
        ),
    ]
