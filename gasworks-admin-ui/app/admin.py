import requests
from django.contrib import admin
from .models import User, Customer, MeterReading
from .servicies import get_customer_list, get_meterreading_list, get_user_list, get_customer_post_base_url, get_user_post_base_url, get_meterreading_post_base_url

@admin.register(User)
class UserAdmin(admin.ModelAdmin):
    list_display = ("id", "name", "email", "role", "created_at", "updated_at")
    search_fields = ("name", "email", "role")

    def get_queryset(self, request):
        data = get_user_list()

        queryset = []
        for item in data:
            obj = User(id=item['id'], customer_id=item['customer_id'], user_id=item['user_id'], reading_date=item['reading_date'], value=item['value'], created_at=['created_at'], updated_at=['updated_at'])
            queryset.append(obj)
        
        return queryset

    def save_model(self, request, obj, form, change):
        payload = {
            'id': obj.id, 
            'name': obj.name, 
            'email': obj.email, 
            'role': obj.role, 
            'password': obj.password,
        }

        if change:
            url = f"{get_user_post_base_url()}/{obj.id}"
            requests.put(url, json=payload)
        else:
            url = get_user_post_base_url()
            requests.post(url, json=payload)

    def delete_model(self, request, obj):
        requests.delete(f"{get_user_post_base_url()}/{obj.id}")


@admin.register(Customer)
class CustomerAdmin(admin.ModelAdmin):
    list_display = ("id", "name", "address", "contact", "created_at", "updated_at")
    search_fields = ("name", "address", "contact")

    def get_queryset(self, request):
        data = get_customer_list()

        queryset = []
        for item in data:
            obj = Customer(id=item['id'], customer_id=item['customer_id'], user_id=item['user_id'], reading_date=item['reading_date'], value=item['value'], created_at=['created_at'], updated_at=['updated_at'])
            queryset.append(obj)
        
        return queryset

    def save_model(self, request, obj, form, change):
        payload = {
            'id': obj.id,
            'name': obj.name,
            'address': obj.address,
            'contact': obj.contact,
        }

        if change:
            url = f"{get_customer_post_base_url()}/{obj.id}"
            requests.put(url, json=payload)
        else:
            url = get_customer_post_base_url()
            requests.post(url, json=payload)

    def delete_model(self, request, obj):
        requests.delete(f"{get_customer_post_base_url()}/{obj.id}")


@admin.register(MeterReading)
class MeterReadingAdmin(admin.ModelAdmin):
    list_display = ("id", "customer_id", "user_id", "reading_date", "reading_value", "created_at", "updated_at")
    search_fields = ("customer_id", "user_id", "reading_date")
    list_filter = ("customer_id", "user_id", "reading_date")

    def get_queryset(self, request):
        data = get_meterreading_list()

        queryset = []
        for item in data:
            obj = MeterReading(id=item['id'], customer_id=item['customer_id'], user_id=item['user_id'], reading_date=item['reading_date'], value=item['value'], created_at=['created_at'], updated_at=['updated_at'])
            queryset.append(obj)
        
        return queryset

    def save_model(self, request, obj, form, change):
        payload = {
            'id': obj.id,
            'customer_id': obj.customer_id.id if hasattr(obj, 'customer_id') and obj.customer_id else getattr(obj, 'customer_id_id', None),
            'user_id': obj.user_id.id if hasattr(obj, 'user_id') and obj.user_id else getattr(obj, 'user_id_id', None),
            'reading_date': str(obj.reading_date) if obj.reading_date else None,
            'reading_value': float(obj.reading_value) if obj.reading_value else None,
        }

        if change:
            url = f"{get_meterreading_post_base_url()}/{obj.id}"
            requests.put(url, json=payload)
        else:
            url = get_meterreading_post_base_url()
            requests.post(url, json=payload)

    def delete_model(self, request, obj):
        requests.delete(f"{get_meterreading_post_base_url()}/{obj.id}")