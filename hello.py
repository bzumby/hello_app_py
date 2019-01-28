from flask import Flask
app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello World! The deployment should work now!'

if __name__ == '__main__':
    app.run(host='0.0.0.0')
