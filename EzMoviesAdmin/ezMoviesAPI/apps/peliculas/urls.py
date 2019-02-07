from .views import *
from django.urls.conf import path

urlpatterns = [
    path('api/pelicula', PeliculaGetPost.as_view(), name="api_peliculas"),
    path('api/actor', ActorGetPost.as_view(), name="api_actores"),
    path('api/generos', GeneroGetPost.as_view(), name="api_generos"),
    path('api/pelicula/<int:id>/update', PeliculaUpdate.as_view(), name="pelicula_put"),
    path('api/actor/<int:id>/update', ActorUpdate.as_view(), name="actor_put"),
    path('api/generos/<int:id>/update', GeneroUpdate.as_view(), name="genero_put"),
    path('api/pelicula/<int:id>/delete', PeliculaDelete.as_view(), name="pelicula_delete"),
    path('api/actor/<int:id>/delete', ActorDelete.as_view(), name="actor_delete"),
    path('api/generos/<int:id>/delete', GeneroDelete.as_view(), name="genero_delete"),

]