class a:
    def __init__(self, a, b):
        self.a = a
        self.b = b

    def __iter__(self):
        yield self.a
        yield self.b

asd = a(1, 2)

asd1, asd2 = asd

print(asd1, asd2)
