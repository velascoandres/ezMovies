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

#  Actualizar
class PeliculaUpdate(generics.UpdateAPIView):
    queryset = Pelicula.objects.all()
    lookup_field = 'id'
    serializer_class = PeliculaSerializada_put

#  Actualizar
class GeneroUpdate(generics.UpdateAPIView):
    queryset = Genero.objects.all()
    lookup_field = 'id'
    serializer_class = GeneroSerializado


#  Actualizar
class ActorUpdate(generics.UpdateAPIView):
    queryset = Actor.objects.all()
    lookup_field = 'id'
    serializer_class = ActorSerializado


# Borrar
class PeliculaDelete(generics.DestroyAPIView):
    queryset = Pelicula.objects.all()
    lookup_field = 'id'
    serializer_class = PeliculaSerializada
    http_method_names = ["delete"]
    allowed_methods = ('DELETE',)


# Borrar
class GeneroDelete(generics.DestroyAPIView):
    queryset = Genero.objects.all()
    lookup_field = 'id'
    serializer_class = GeneroSerializado
    http_method_names = ["delete"]
    allowed_methods = ('DELETE',)

# Borrar
class ActorDelete(generics.DestroyAPIView):
    queryset = Actor.objects.all()
    lookup_field = 'id'
    serializer_class = ActorSerializado
    http_method_names = ["delete"]
    allowed_methods = ('DELETE',)


