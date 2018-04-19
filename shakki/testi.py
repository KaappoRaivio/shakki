class a:
    def __init__(self, a, b):
        self.a = a
        self.b = b

    def __unpack__(self):
        return self.a, self.b

asd = a(1, 2)

lista = *asd
