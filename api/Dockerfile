# Используем официальный образ OpenJDK
FROM eclipse-temurin:17-jdk-alpine

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем JAR-файл приложения в контейнер
COPY ./*.jar app.jar

# Открываем порт, на котором работает приложение
EXPOSE 9090

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]