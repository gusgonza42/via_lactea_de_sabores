import csv
import random
import os
from faker import Faker

fake = Faker('es_ES')


def escribir_csv(datos, nombre_archivo, headers):
    ruta_carpeta = os.path.join(os.getcwd(),
                                'datos_generados_csv')  # Reemplaza 'carpeta_csv' con el nombre de tu carpeta
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


# generar datos aleatorios para la tabla mesa
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
    menus_lista = ['Nebulosas del Sabor', 'Ruta Galáctica', 'La Nasa', 'Sinfonía de Sabores', 'Viaje Cósmico']
    datos = []
    for i in range(num_datos):
        id = i + 1
        nombre_menu = random.choice(menus_lista)
        descripcion = fake.sentence()
        precio = round(random.uniform(5, 50), 2)

        datos.append((id, nombre_menu, descripcion, precio))

    return datos


# Generar datos aleatorios para la tabla intermedia entre menu y comanda
def generar_datos_menu_comanda(num_menus, num_comandas):
    datos = []
    alergenos_lista = ['gluten', 'crustáceos', 'huevos', 'pescado', 'cacahuetes', 'soja', 'leche', 'frutos de cáscara', 'apio', 'mostaza', 'sésamo', 'sulfitos', 'moluscos']

    for menu_id in range(1, num_menus + 1):
        comandas_seleccionadas = random.sample(range(1, num_comandas + 1), random.randint(1, 5))
        for comanda_id in comandas_seleccionadas:
            alergenos = ', '.join(random.sample(alergenos_lista, random.randint(1, 4)))
            datos.append((menu_id, comanda_id, alergenos))

    return datos


# Generar datos aleatorios para la tabla de platos
def generar_datos_platos(num_datos):
    platos_referentes = ['Crema de', 'Leche de', 'Queso de', 'Mantequilla de', 'Nata de', 'Yogur de', 'Helado de', 'Espuma de']

    datos = []
    for i in range(num_datos):
        id = i + 1
        nombre_plato = random.choice(platos_referentes) + " " + fake.word()
        descripcion = fake.sentence()
        id_chef = random.randint(1, num_datos)
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
    productos_referentes = ['Foie Gras', 'Trufa Negra', 'Carrillera de Buey Wagyu', 'Caviar Beluga', 'Ostras Gillardeau', 'Salmón Salvaje de Alaska', 'Langosta de Maine', 'Kobe Beef', 'Cangrejo Real', 'Caracoles de Borgoña']
    datos = []
    for i in range(num_datos):
        id_producto = i + 1
        nombre = random.choice(productos_referentes) + " " + fake.word()
        stock = random.randint(1, 100)
        proveedor = random.choice(proveedores)

        datos.append((id_producto, nombre, stock, proveedor))

    return datos


# Generar datos aleatorios para la tabla intermedia entre platos y productos
def generar_datos_platos_productos(num_platos, num_productos):
    datos = []
    for plato_id in range(1, num_platos + 1):
        productos_seleccionados = random.sample(range(1, num_productos + 1), random.randint(1, 5))
        for producto_id in productos_seleccionados:
            datos.append((plato_id, producto_id))

    return datos


# Escribir los datos en archivos CSV
headers_usuarios = ['id', 'nombre', 'apellido1', 'apellido2', 'fecha_nacimiento', 'email', 'fecha_registro', 'telefono']
datos_usuarios = generar_datos_usuarios(25)
escribir_csv(datos_usuarios, 'usuarios.csv', headers_usuarios)

headers_chefs = ['id_usuario', 'salario', 'fecha_contratacion', 'disponible']
datos_chefs = generar_datos_chefs(25)
escribir_csv(datos_chefs, 'chefs.csv', headers_chefs)

headers_camareros = ['id_usuario', 'salario', 'fecha_contratacion', 'disponibilidad']
datos_camareros = generar_datos_camareros(25)
escribir_csv(datos_camareros, 'camareros.csv', headers_camareros)

headers_mesas = ['num_mesa', 'id_camarero', 'fecha_registro', 'disponibilidad']
datos_mesas = generar_datos_mesas(25)
escribir_csv(datos_mesas, 'mesas.csv', headers_mesas)

headers_clientes = ['id_usuario', 'comensales', 'fecha_reserva']
datos_clientes = generar_datos_clientes(25)
escribir_csv(datos_clientes, 'clientes.csv', headers_clientes)

headers_clientes_mesas = ['id_usuario', 'num_mesa', 'fecha_reserva']
datos_clientes_mesas = generar_datos_cliente_mesa(25, 25)
escribir_csv(datos_clientes_mesas, 'clientes_mesas.csv', headers_clientes_mesas)

headers_comandas = ['id_comanda', 'fecha', 'pagado', 'num_mesa']
datos_comandas = generar_datos_comandas(25)
escribir_csv(datos_comandas, 'comandas.csv', headers_comandas)

headers_menus = ['id_menu', 'nombre_menu', 'descripcion', 'precio']
datos_menus = generar_datos_menus(25)
escribir_csv(datos_menus, 'menus.csv', headers_menus)

headers_menus_comandas = ['id_menu', 'id_comanda', 'alergenos']
datos_menus_comandas = generar_datos_menu_comanda(25, 25)
escribir_csv(datos_menus_comandas, 'menus_comandas.csv', headers_menus_comandas)

headers_platos = ['id_plato', 'nombre_plato', 'descripcion', 'id_chef']
datos_platos = generar_datos_platos(25)
escribir_csv(datos_platos, 'platos.csv', headers_platos)

headers_menus_platos = ['id_menu', 'id_plato']
datos_menus_platos = generar_datos_menus_platos(25, 25)
escribir_csv(datos_menus_platos, 'menus_platos.csv', headers_menus_platos)

headers_productos = ['id_producto', 'nombre', 'stock', 'proveedor']
datos_productos = generar_datos_productos(25)
escribir_csv(datos_productos, 'productos.csv', headers_productos)

headers_platos_productos = ['id_plato', 'id_producto']
datos_platos_productos = generar_datos_platos_productos(25, 25)
escribir_csv(datos_platos_productos, 'platos_productos.csv', headers_platos_productos)
