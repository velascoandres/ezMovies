from django.db import router

from .views import *
from django.urls.conf import path

urlpatterns = [
    path('index', index_comentario, name='index_comentario'),
    path('api/', ComentarioAPI.as_view(), name="api_comentarios"),
    path('api/<int:id>/', ComentarioUpdate.as_view(), name="comentarios_update"),
    path('api/<int:id>/delete', ComentarioDelete.as_view(), name="comentarios_delete"),
]
