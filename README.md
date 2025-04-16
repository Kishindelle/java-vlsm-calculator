# 📡 VLSM Calculator - Java

Este es un proyecto de **Calculadora VLSM (Variable Length Subnet Mask)** desarrollado en Java. Permite calcular subredes de longitud variable a partir de una IP y una lista de requerimientos, optimizando el uso del espacio de direcciones IPv4.

## 🚀 Características

- Validación de IPs base (formato correcto, rango permitido).
- Verificación de requerimientos de subredes.
- Cálculo automático de subredes con máscaras variables.
- Detección de casos en los que:
  - La IP no satisface los requerimientos.
  - Los requerimientos exceden el espacio disponible.
  - Las IPs ingresadas son inválidas.
- Salida estructurada con los rangos de IP, dirección de red, broadcast y máscara.

## ⚠️ Estado del proyecto

> ⚠️ **Este proyecto no ha sido testeado al 100%.**

Aunque se han realizado múltiples pruebas con distintos escenarios (IPs válidas, no válidas, insuficientes y sobrantes), **existe la posibilidad de que algunas IPs específicas puedan generar errores o resultados inesperados en el proceso de subneteo**. Se recomienda precaución si se planea usar esta herramienta en entornos críticos o de producción.

## 🛠️ Tecnologías usadas

- Java 8 o superior
- No Builds

## 🖥️ Cómo usar

1. Clona el repositorio:
   ```bash
   git clone https://github.com/Kishindelle/java-vlsm-calculator.git
   ```
