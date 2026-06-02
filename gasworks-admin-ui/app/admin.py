from django.contrib import admin
from .models import User, Customer, MeterReading

@admin.register(User)
class UserAdmin(admin.ModelAdmin):
    list_display = ("id", "name", "email", "role", "created_at", "updated_at")
    search_fields = ("name", "email", "role")

@admin.register(Customer)
class CustomerAdmin(admin.ModelAdmin):
    list_display = ("id", "name", "address", "contact", "created_at", "updated_at")
    search_fields = ("name", "address", "contact")

@admin.register(MeterReading)
class MeterReadingAdmin(admin.ModelAdmin):
    list_display = ("id", "customer_id", "user_id", "reading_date", "reading_value", "created_at", "updated_at")
    search_fields = ("customer_id", "user_id", "reading_date")
    list_filter = ("customer_id", "user_id", "reading_date")