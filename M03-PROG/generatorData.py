import csv
import random
from faker import Faker
from datetime import datetime

fake = Faker('es_ES')

# Escribir los datos en un archivo TXT
def escribir_txt(datos, nombre_archivo):
    with open(nombre_archivo, mode='w', newline='', encoding='utf-8') as file:
        for dato in datos:
            file.write(','.join(map(str, dato)) + '\n')

# Generar datos aleatorios para la tabla de usuarios
def generar_datos_usuarios(num_datos):
    datos = []
    for _ in range(num_datos):
        nombre = fake.first_name()
        apellido1 = fake.last_name()
        apellido2 = fake.last_name()
        fecha_nacimiento = fake.date_of_birth(minimum_age=18, maximum_age=90)
        email = fake.email()
        fecha_registro = fake.date_between(start_date='-1y', end_date='today')
        telefono = fake.phone_number()

        datos.append((nombre, apellido1, apellido2, fecha_nacimiento, email, fecha_registro, telefono))

    return datos

# Generar datos aleatorios para la tabla de camareros
def generar_datos_camareros(num_datos):
    datos = []
    for _ in range(num_datos):
        salario = round(random.uniform(1000, 3000), 2)
        fecha_contratacion = fake.date_between(start_date='-5y', end_date='today')
        disponibilidad = random.choice([True, False])

        datos.append((salario, fecha_contratacion, disponibilidad))

    return datos

# Generar datos aleatorios para la tabla de mesas
def generar_datos_mesas(num_datos):
    datos = []
    for _ in range(num_datos):
        num_mesa = random.randint(1, 50)
        id_camarero = random.randint(1, num_datos)  # asumiendo que hay el mismo número de camareros que de mesas
        fecha_registro = fake.date_between(start_date='-1y', end_date='today')

        datos.append((num_mesa, id_camarero, fecha_registro))

    return datos

# Generar datos aleatorios para la tabla de clientes
def generar_datos_clientes(num_datos):
    datos = []
    for _ in range(num_datos):
        comensales = random.randint(1, 10)
        fecha_reserva = fake.date_between(start_date='-1y', end_date='today')

        datos.append((comensales, fecha_reserva))

    return datos

# Generar datos aleatorios para la tabla de comandas
def generar_datos_comandas(num_datos):
    datos = []
    for _ in range(num_datos):
        fecha = fake.date_between(start_date='-1y', end_date='today')
        pagado = random.choice([True, False])
        num_mesa = random.randint(1, 50)  # asumiendo que hay 50 mesas en total

        datos.append((fecha, pagado, num_mesa))

    return datos

# Generar datos aleatorios para la tabla de chefs
def generar_datos_chefs(num_datos):
    datos = []
    for _ in range(num_datos):
        salario = round(random.uniform(2000, 8000), 2)
        fecha_contratacion = fake.date_between(start_date='-5y', end_date='today')
        disponible = random.choice([True, False])

        datos.append((salario, fecha_contratacion, disponible))

    return datos

# Generar datos aleatorios para la tabla de platos
def generar_datos_platos(num_datos):
    datos = []
    for _ in range(num_datos):
        nombre_plato = fake.word()
        descripcion = fake.sentence()
        id_chef = random.randint(1, num_datos)  # asumiendo que hay el mismo número de chefs que de platos

        datos.append((nombre_plato, descripcion, id_chef))

    return datos

# Generar datos aleatorios para la tabla de productos
def generar_datos_productos(num_datos):
    proveedores = ['Rungis Market', 'Miyazaki Wagyu', 'Tsar Nicoulai Caviar']
    datos = []
    for _ in range(num_datos):
        nombre = fake.word()
        stock = random.randint(0, 100)
        proveedor = random.choice(proveedores)

        datos.append((nombre, stock, proveedor))

    return datos

# Generar datos aleatorios para la tabla de menús
def generar_datos_menus(num_datos):
    datos = []
    for _ in range(num_datos):
        nombre_menu = fake.word()
        descripcion = fake.sentence()
        precio = round(random.uniform(5, 50), 2)

        datos.append((nombre_menu, descripcion, precio))

    return datos

# Generar datos aleatorios para la tabla intermedia entre menu y comanda
def generar_datos_menu_comanda(num_menus, num_comandas):
    datos = []
    for menu_id in range(1, num_menus + 1):
        comandas_seleccionadas = random.sample(range(1, num_comandas + 1), random.randint(1, 5))
        for comanda_id in comandas_seleccionadas:
            datos.append((menu_id, comanda_id))

    return datos

# Generar datos aleatorios para la tabla intermedia entre menús y platos
def generar_datos_menus_platos(num_menus, num_platos):
    datos = []
    for menu_id in range(1, num_menus + 1):
        platos_seleccionados = random.sample(range(1, num_platos + 1), random.randint(1, 5))
        for plato_id in platos_seleccionados:
            datos.append((menu_id, plato_id))

    return datos

# Generar datos aleatorios para la tabla intermedia entre platos e ingredientes
def generar_datos_platos_ingredientes(num_platos, num_ingredientes):
    datos = []
    for plato_id in range(1, num_platos + 1):
        ingredientes_seleccionados = random.sample(range(1, num_ingredientes + 1), random.randint(1, 5))
        for ingrediente_id in ingredientes_seleccionados:
            datos.append((plato_id, ingrediente_id))

    return datos

# Generar datos aleatorios para la tabla intermedia entre ingredientes y alérgenos
def generar_datos_ingredientes_alergenos(num_datos):
    datos = []
    for i in range(1, num_datos + 1):
        # Seleccionar un conjunto aleatorio de alérgenos para cada ingrediente
        alergenos_seleccionados = random.sample(range(1, 51), random.randint(1, 10))
        for alergeno in alergenos_seleccionados:
            datos.append((i, alergeno))

    return datos

# Escribir los datos en archivos TXT
datos_usuarios = generar_datos_usuarios(25)
escribir_txt(datos_usuarios, 'Usuaris.txt')

datos_camareros = generar_datos_camareros(25)
escribir_txt(datos_camareros, 'Cambrers.txt')

datos_mesas = generar_datos_mesas(25)
escribir_txt(datos_mesas, 'Taules.txt')

datos_clientes = generar_datos_clientes(25)
escribir_txt(datos_clientes, 'Clients.txt')

datos_comandas = generar_datos_comandas(25)
escribir_txt(datos_comandas, 'Comandes.txt')

datos_chefs = generar_datos_chefs(25)
escribir_txt(datos_chefs, 'Cuiners.txt')

datos_platos = generar_datos_platos(25)
escribir_txt(datos_platos, 'Plats.txt')

datos_productos = generar_datos_productos(25)
escribir_txt(datos_productos, 'Ingredients.txt')

datos_ingredientes_alergenos = generar_datos_ingredientes_alergenos(25)
escribir_txt(datos_ingredientes_alergenos, 'Ingredients_Alergens.txt')

datos_menus = generar_datos_menus(25)
escribir_txt(datos_menus, 'Menus.txt')

datos_menus_platos = generar_datos_menus_platos(25, 25)
escribir_txt(datos_menus_platos, 'Menus_Plats.txt')

datos_platos_ingredientes = generar_datos_platos_ingredientes(25, 25)
escribir_txt(datos_platos_ingredientes, 'Plats_Ingredients.txt')
