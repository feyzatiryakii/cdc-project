import mysql.connector
from mysql.connector import Error
from faker import Faker  #gercekci yapay data uretmek icin
import time

faker = Faker()

#db baglan
def connectdb(retries=10, delay=5):
    for i in range(retries):
        try:
            return mysql.connector.connect(
                host="mysql",
                user="root",
                password="1234",
                database="cdc_demo"
            )
        except Error as e:
            print(f"Bağlantı denemesi {i+1}/{retries} başarısız: {e}")
            time.sleep(delay)
    raise Exception("MySQL bağlantısı sağlanamadı.")

#customer data uretsin
def generateData():
    return {
        "isim": faker.first_name(),
        "soyisim": faker.last_name(),
        "mail": faker.email(),
        "sifre": faker.password(length=10)   
    }

#datayi dbye gecir
def insertData(cursor, customer):
    query = """
    INSERT INTO customers (isim, soyisim, mail, sifre)
    VALUES (%s, %s, %s, %s)
    """
    values = (customer['isim'], customer['soyisim'], customer['mail'], customer['sifre'])  
    cursor.execute(query, values)


def run_limited_inserts(limit):
    conn = connectdb()
    cursor = conn.cursor()
    try:
        for i in range(limit):
            customer = generateData()
            insertData(cursor, customer)
            conn.commit()
            print(f"[{i+1}] Yeni customer eklendi: {customer}")
            time.sleep(2)
    finally:
        cursor.close()
        conn.close()    


def run_infinite_inserts():
    conn = connectdb()                
    cursor = conn.cursor() 
    try:
        while True:
            customer = generateData()
            insertData(cursor, customer)
            conn.commit()
            print(f"[+] Yeni customer eklendi: {customer}")  
            time.sleep(10)
    except KeyboardInterrupt:
        print("İşlem kullanıcı tarafından durduruldu.")
    finally:
        cursor.close()
        conn.close()

if __name__ == "__main__":
    run_infinite_inserts()
