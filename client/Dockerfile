FROM alpine:3.13.6
VOLUME /tmp
COPY index.html index.html
COPY client.js client.js
RUN apk add --no-cache python3 py3-pip
ENTRYPOINT ["python3", "-m", "http.server"]
