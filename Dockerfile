FROM amd64/amazoncorretto:17

WORKDIR /app

COPY ./toaster-bootstrap/build/libs/*.jar /app/app.jar

CMD ["java", "-Duser.timezone=Asia/Seoul", "-jar", "-Dspring.profiles.active=dev", "app.jar"]
