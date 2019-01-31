from django.http import HttpResponse
from django.shortcuts import render


# Create your views here.
from rest_framework import generics
from rest_framework.permissions import IsAuthenticated

from apps.comentarios.models import Comentario
from apps.comentarios.serializers import ComentarioSerializado


def index_comentario(request):
    return HttpResponse('Estas en comentarios')


# Listar y crear
class ComentarioAPI(generics.ListCreateAPIView):
    queryset = Comentario.objects.all()
    serializer_class = ComentarioSerializado



#Actualizar
class ComentarioUpdate(generics.UpdateAPIView):
    queryset = Comentario.objects.all()
    lookup_field = 'id'
    serializer_class = ComentarioSerializado


# Borrar
class ComentarioDelete(generics.DestroyAPIView):
    queryset = Comentario.objects.all()
    lookup_field = 'id'
    serializer_class = ComentarioSerializado
    http_method_names = ["delete"]
    allowed_methods = ('DELETE',)
