FROM alpine:latest

LABEL maintainer="valera"

RUN apk add --update \
    python \
    python-dev \
    py-pip \
    && pip install virtualenv

EXPOSE 5000

WORKDIR /app

COPY . /app
RUN virtualenv /env && /env/bin/pip install -r /app/requirements.txt

CMD ["/env/bin/python", "hello.py"]


