from django.db import models


# Create your models here.
class Pelicula(models.Model):
    nombre = models.CharField(max_length=30, null=False, blank=False)
    descripcion = models.CharField(max_length=200, null=False, blank=False)
    caratula = models.ImageField(upload_to='media', default="ninguna", null=True)
    costo = models.DecimalField(null=False, blank=False, max_digits=5, decimal_places=2)
    generos = models.ManyToManyField('Genero', related_name="GeneroPelicula")


class Genero(models.Model):
    nombre = models.CharField(max_length=30, null=False, blank=False)


class Actor(models.Model):
    nombre = models.CharField(max_length=30, null=False, blank=False)
    peliculas = models.ManyToManyField('Pelicula', related_name="ActorPelicula", symmetrical=False)



