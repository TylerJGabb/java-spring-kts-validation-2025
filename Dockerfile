FROM doitintl/secrets-init:0.5.3 as with-secrets-init
FROM gradle:8.13-jdk17 as build
WORKDIR /workdir
COPY . .
RUN gradle --no-daemon build -x test

FROM openjdk:17 as runtime
COPY --from=with-secrets-init /secrets-init /secrets-init
COPY --from=build /workdir/build/libs/*.jar /app.jar

CMD /secrets-init --exit-early --provider=google sh -c 'java -jar /app.jar'


