from rest_framework import serializers
from apps.peliculas.models import *


class GeneroSerializado(serializers.ModelSerializer):
    class Meta:
        model = Genero
        fields = '__all__'


class PeliculaSerializada(serializers.ModelSerializer):
    generos = serializers.PrimaryKeyRelatedField(queryset=Genero.objects.all(), many=True, required=False)

    class Meta:
        model = Pelicula
        fields = ('id', 'nombre', 'descripcion', 'caratula', 'costo', 'generos')


class PeliculaSerializada_put(serializers.ModelSerializer):
    generos = serializers.PrimaryKeyRelatedField(queryset=Genero.objects.all(), many=True, required=False)

    class Meta:
        model = Pelicula
        fields = ('id', 'nombre', 'descripcion', 'costo', 'generos')


class ActorSerializado(serializers.ModelSerializer):
    peliculas = serializers.PrimaryKeyRelatedField(queryset=Pelicula.objects.all(), many=True, required=False)

    class Meta:
        model = Actor
        fields = ('id', 'nombre', 'peliculas')
