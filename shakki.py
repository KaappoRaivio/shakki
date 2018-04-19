class SuhteellinenKoordinaatti:
    def __init__(self, delta_x, delta_y):
        self.x = delta_x
        self.y = delta_y

    def __iter__(self):
        yield self.x
        yield self.y

    @property
    def x(self):
        return x

    @x.setter
    def x(self, val):
        if val > 8:
            self.x = 8
        elif val < -8:
            self.x = -8
        else:
            self.x = val

    @property
    def y(self):
        return y

    @y.setter
    def y(self, val):
        if val > 8:
            self.y = 8
        elif val < -8:
            self.y = -8
        else:
            self.y = val


class Shakkiruutu:

    __id = 0

    def __init__(self, koordinaatti, nappula):
        self.__pos_x, self.__pos_y = pos_y = *koordinaatti

        self.nappula = nappula

        self.__id = Shakkiruutu.__id
        Shakkiruutu.__id += 1

    @property
    def nappula(self):
        return self.nappula

    @nappula.setter
    def nappula(self, val):
        if type(val) in [None, Sotilas, Torni, Ratsu, Lähetti, Kuningas, Kuningatar]:
            self.nappula = val

    @property
    def pos_x(self):
        return self.pos_x

    @pos_x.setter
    def pos_x(self, val):
        print("Ei onnistu pos_x:n asetus")

    @property
    def pos_y(self):
        return self.pos_y

    @pos_y.setter
    def pos_y(self, val):
        print("Ei onnistu pos_x:n asetus")


class AbsoluuttinenKoordinaatti:
    def __init__(self, pos_x, pos_y):
        self.x = pos_x
        self.y = pos_y

    def __iter__(self):
        yield self.x
        yield self.y

    @property
    def x(self):
        return x

    @x.setter
    def x(self, val):
        if val > 8:
            self.x = 8
        elif val < -8:
            self.x = -8
        else:
            self.x = val

    @property
    def y(self):
        return y

    @y.setter
    def y(self, val):
        if val > 8:
            self.y = 8
        elif val < -8:
            self.y = -8
        else:
            self.y = val


class Shakkilauta:
    def __init__(self):
        self.ruudut = [[Shakkiruutu(x, y, None) for x in range(9)] for y in range(9)]


class Shakkinappula:
    """Älä instantioi tästä!!!"""

    def __init__(self, allowed_squares, sijainti):
        self.relative_allowed_squares = allowed_squares
        self.pos_x, self.pos_y = *sijainti

    def __iter__(self):



class Sotilas(Shakkinappula):
    def __init__(self, pos_from_left):

        super().__init__(self)


class Torni(Shakkinappula):
    pass


class Ratsu(Shakkinappula):
    pass


class Lähetti(Shakkinappula):
    pass


class Kuningas(Shakkinappula):
    pass


class Kuningatar(Shakkinappula):
    pass


a = Shakkilauta()
print(a.ruudut)
