# Generated by Django 2.1.5 on 2019-02-07 09:11

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('peliculas', '0002_auto_20190207_0315'),
    ]

    operations = [
        migrations.AlterField(
            model_name='pelicula',
            name='caratula',
            field=models.ImageField(default='ninguna', upload_to='media'),
        ),
    ]