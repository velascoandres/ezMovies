from rest_framework import serializers
from apps.comentarios.models import Comentario


class ComentarioSerializado(serializers.ModelSerializer):
    class Meta:
        model = Comentario
        fields = '__all__'
