# Create your views here.

# Listar y crear
from rest_framework import generics

from apps.peliculas.serializers import *


class PeliculaGetPost(generics.ListCreateAPIView):
    queryset = Pelicula.objects.all()
    serializer_class = PeliculaSerializada


class GeneroGetPost(generics.ListCreateAPIView):
    queryset = Genero.objects.all()
    serializer_class = GeneroSerializado


class ActorGetPost(generics.ListCreateAPIView):
    queryset = Actor.objects.all()
    serializer_class = ActorSerializado
