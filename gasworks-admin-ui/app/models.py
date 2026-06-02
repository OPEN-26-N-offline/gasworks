from django.db import models

class User(models.Model):
    id = models.AutoField("ユーザーID", primary_key=True)
    name = models.CharField("ユーザー名", max_length=255)
    email = models.EmailField("メールアドレス", max_length=255, unique=True)
    password = models.CharField("パスワード", max_length=255)
    role = models.CharField("役割", max_length=50)
    created_at = models.DateTimeField("作成日時", auto_now_add=True)
    updated_at = models.DateTimeField("更新日時", auto_now=True)

    class Meta:
        db_table = "users"
        verbose_name = "ユーザー情報"
        verbose_name_plural = "ユーザー情報一覧"
        managed = True

    def __str__(self):
        return f"{self.id}: {self.name}"

class Customer(models.Model):
    id = models.AutoField("顧客ID", primary_key=True)
    name = models.CharField("顧客名",max_length=255)
    address = models.CharField("住所",max_length=255)
    contact = models.CharField("連絡先",max_length=255)
    created_at = models.DateTimeField("作成日時", auto_now_add=True)
    updated_at = models.DateTimeField("更新日時", auto_now=True)

    class Meta:
        db_table = "customers"
        verbose_name = "顧客情報"
        verbose_name_plural = "顧客情報一覧"
        managed = True

    def __str__(self):
        return f"{self.id}: {self.name}"

class MeterReading(models.Model):
    id = models.AutoField("メーター読数ID", primary_key=True)
    customer_id = models.ForeignKey(Customer, on_delete=models.CASCADE, verbose_name="顧客ID")
    user_id = models.ForeignKey(User, on_delete=models.CASCADE, verbose_name="作業員ID")
    reading_date = models.DateField("読取日")
    reading_value = models.DecimalField("読取値", max_digits=10, decimal_places=2)
    created_at = models.DateTimeField("作成日時", auto_now_add=True)    
    updated_at = models.DateTimeField("更新日時", auto_now=True)

    class Meta:
        db_table = "meter_readings"
        verbose_name = "検針データ"
        verbose_name_plural = "検針データ一覧"
        managed = True


    def __str__(self):
        return f"{self.customer_id} - {self.reading_date}"
