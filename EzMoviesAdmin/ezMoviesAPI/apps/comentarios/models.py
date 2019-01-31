from django.db import models


# Create your models here.
class Comentario(models.Model):
    imagen = models.ImageField(default='http://image10.bizrate-images.com/resize?sq=60&uid=2216744464', upload_to='media')
    fecha_creacion = models.DateField(null=False, blank=False, auto_now=True)
    contenido = models.CharField(null=False, blank=False, max_length=150)

    def __str__(self):
        """Django uses when it needs to convert the object to a string"""
        return str(self.id)