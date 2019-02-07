from .views import *
from django.urls.conf import path

urlpatterns = [
    path('api/pelicula', PeliculaGetPost.as_view(), name="api_peliculas"),
    path('api/actor', ActorGetPost.as_view(), name="api_actores"),
    path('api/generos', GeneroGetPost.as_view(), name="api_generos"),
]