FROM java:8

RUN mkdir /app && \
    mkdir /app/code

COPY ./ /app/code

WORKDIR /app/code
RUN build-hooks/pre-deploy

CMD ["java", "-jar", "build/libs/nigerianpostcodes-api-0.0.1.jar"]
