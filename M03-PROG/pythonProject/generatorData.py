import csv
import random
import os
from faker import Faker

fake = Faker('es_ES')


def escribir_csv(datos, nombre_archivo, headers):
    ruta_carpeta = os.path.join(os.getcwd(), 'datos_generados_csv')  # Reemplaza 'carpeta_csv' con el nombre de tu carpeta
    os.makedirs(ruta_carpeta, exist_ok=True)  # Crear la carpeta si no existe
    ruta_archivo = os.path.join(ruta_carpeta, nombre_archivo)
    with open(ruta_archivo, mode='w', newline='', encoding='utf-8') as file:
        writer = csv.writer(file)
        writer.writerow(headers)
        writer.writerows(datos)


# Generar datos aleatorios para la tabla de usuarios
def generar_datos_usuarios(num_datos):
    datos = []
    for i in range(num_datos):
        id = i + 1
        nombre = fake.first_name()
        apellido1 = fake.last_name()
        apellido2 = fake.last_name()
        fecha_nacimiento = fake.date_of_birth(minimum_age=18, maximum_age=90)
        email = fake.email()
        fecha_registro = fake.date_between(start_date='-1y', end_date='today')
        telefono = generar_telefono()

        datos.append((id, nombre, apellido1, apellido2, fecha_nacimiento, email, fecha_registro, telefono))

    return datos


# Conjunto para almacenar los números de teléfono generados
telefonos_generados = set()


def generar_telefono():
    telefono = f'{random.randint(600000000, 699999999)}'
    # Comprobar si el número de teléfono ya ha sido generado
    while telefono in telefonos_generados:
        telefono = f'{random.randint(600000000, 699999999)}'
    # Añadir el número de teléfono al conjunto de números generados
    telefonos_generados.add(telefono)
    return telefono


# Generar datos aleatorios para la tabla de chefs
def generar_datos_chefs(num_datos):
    datos = []
    for i in range(num_datos):
        id = i + 1
        salario = round(random.uniform(2000, 8000), 2)
        fecha_contratacion: object = fake.date_between(start_date='-5y', end_date='today')
        disponible = random.choice([True, False])

        datos.append((id, salario, fecha_contratacion, disponible))

    return datos


# Generar datos aleatorios para la tabla de camareros
def generar_datos_camareros(num_datos):
    datos = []
    for i in range(num_datos):
        id = i + 1
        salario = round(random.uniform(1000, 3000), 2)
        fecha_contratacion = fake.date_between(start_date='-5y', end_date='today')
        disponibilidad = random.choice([True, False])

        datos.append((id, salario, fecha_contratacion, disponibilidad))

    return datos


# generar datos aleatorios para la tabal mesa
def generar_datos_mesas(num_datos):
    datos = []
    for i in range(num_datos):
        num_mesa = i + 1
        id_camarero = random.randint(1, num_datos)  # asumiendo que hay el mismo número de mesas que de camareros
        fecha_registro = fake.date_between(start_date='-1y', end_date='today')
        disponibilidad = random.choice([True, False])
        datos.append((num_mesa, id_camarero, fecha_registro, disponibilidad))

    return datos


# Generar datos aleatorios para la tabla de clientes
def generar_datos_clientes(num_datos):
    datos = []
    for i in range(num_datos):
        id = i + 1
        comensales = random.randint(1, 10)
        fecha_reserva = fake.date_between(start_date='-1y', end_date='today')

        datos.append((id, comensales, fecha_reserva))

    return datos


# Generar datos aleatorios para la tabla intermedia de cliente mesa
def generar_datos_cliente_mesa(num_usuarios, num_mesas):
    datos = []
    for usuario_id in range(1, num_usuarios + 1):
        mesas_reservadas = random.sample(range(1, num_mesas + 1), random.randint(1, 5))
        for mesa_id in mesas_reservadas:
            fecha_reserva = fake.date_between(start_date='-1y', end_date='today')
            datos.append((usuario_id, mesa_id, fecha_reserva))

    return datos


# Generar datos aleatorios para la tabla de comandas
def generar_datos_comandas(num_datos):
    datos = []
    for i in range(num_datos):
        id = i + 1
        fecha = fake.date_between(start_date='-1y', end_date='today')
        pagado = random.choice([True, False])
        num_mesa = random.randint(1, 50)  # asumiendo que hay 50 mesas en total

        datos.append((id, fecha, pagado, num_mesa))

    return datos


# Generar datos aleatorios para la tabla de menús
def generar_datos_menus(num_datos):
    datos = []
    for i in range(num_datos):
        id = i + 1
        nombre_menu = fake.word()
        descripcion = fake.sentence()
        precio = round(random.uniform(5, 50), 2)

        datos.append((id, nombre_menu, descripcion, precio))

    return datos


# Generar datos aleatorios para la tabla intermedia entre menu y comanda
def generar_datos_menu_comanda(num_menus, num_comandas):
    datos = []
    for menu_id in range(1, num_menus + 1):
        comandas_seleccionadas = random.sample(range(1, num_comandas + 1), random.randint(1, 5))
        for comanda_id in comandas_seleccionadas:
            alergenos = fake.sentence()
            datos.append((menu_id, comanda_id, alergenos))

    return datos


# Generar datos aleatorios para la tabla de platos
def generar_datos_platos(num_datos):
    datos = []
    for i in range(num_datos):
        id = i + 1
        nombre_plato = fake.word()
        descripcion = fake.sentence()
        id_chef = random.randint(1, num_datos)  # asumiendo que hay el mismo número de chefs que de platos

        datos.append((id, nombre_plato, descripcion, id_chef))

    return datos


# Generar datos aleatorios para la tabla intermedia entre menús y platos
def generar_datos_menus_platos(num_menus, num_platos):
    datos = []
    for menu_id in range(1, num_menus + 1):
        platos_seleccionados = random.sample(range(1, num_platos + 1), random.randint(1, 5))
        for plato_id in platos_seleccionados:
            datos.append((menu_id, plato_id))

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


# Escribir los datos en archivos CSV
headers_usuarios = ['id', 'nombre', 'apellido1', 'apellido2', 'fecha_nacimiento', 'email', 'fecha_registro', 'telefono']
datos_usuarios = generar_datos_usuarios(25)
escribir_csv(datos_usuarios, 'Usuarios.csv', headers_usuarios)

headers_chefs = ['id', 'salario', 'fecha_contratacion', 'disponible']
datos_chefs = generar_datos_chefs(25)
escribir_csv(datos_chefs, 'Chefs.csv', headers_chefs)

headers_camareros = ['id', 'salario', 'fecha_contratacion', 'disponibilidad']
datos_camareros = generar_datos_camareros(25)
escribir_csv(datos_camareros, 'Camareros.csv', headers_camareros)

headers_mesas = ['id', 'num_mesa', 'id_camarero', 'fecha_registro']
datos_mesas = generar_datos_mesas(25)
escribir_csv(datos_mesas, 'Mesas.csv', headers_mesas)

headers_clientes = ['id', 'comensales', 'fecha_reserva']
datos_clientes = generar_datos_clientes(25)
escribir_csv(datos_clientes, 'Clientes.csv', headers_clientes)

headers_clientes_mesas = ['id', 'id_cliente', 'id_mesa', 'fecha_reserva']
datos_clientes_mesas = generar_datos_cliente_mesa(25, 25)
escribir_csv(datos_clientes_mesas, 'Clientes_Mesas.csv', headers_clientes_mesas)

headers_comandas = ['id', 'fecha', 'pagado', 'num_mesa']
datos_comandas = generar_datos_comandas(25)
escribir_csv(datos_comandas, 'Comandas.csv', headers_comandas)

headers_platos = ['id', 'nombre_plato', 'descripcion', 'id_chef']
datos_platos = generar_datos_platos(25)
escribir_csv(datos_platos, 'Platos.csv', headers_platos)

headers_productos = ['id', 'nombre', 'stock', 'proveedor']
datos_productos = generar_datos_productos(25)
escribir_csv(datos_productos, 'Ingredientes.csv', headers_productos)

headers_menus = ['id', 'nombre_menu', 'descripcion', 'precio']
datos_menus = generar_datos_menus(25)
escribir_csv(datos_menus, 'Menus.csv', headers_menus)

headers_menus_platos = ['id', 'id_menu', 'id_plato']
datos_menus_platos = generar_datos_menus_platos(25, 25)
escribir_csv(datos_menus_platos, 'Menus_Platos.csv', headers_menus_platos)
