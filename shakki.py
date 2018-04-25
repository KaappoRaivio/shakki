from errors import *
from koordinaatti import AbsoluuttinenKoordinaatti, SuhteellinenKoordinaatti
from shakkinappula import Shakkinappula


class Shakkiruutu:

    __id = 0

    def __init__(self, koordinaatti, nappula):
        if isinstance(koordinaatti, AbsoluuttinenKoordinaatti):
            self.__pos_x, self.__pos_y = koordinaatti
        else:
            raise TypeError("Invalid type for the position!")

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


class Shakkilauta:
    def __init__(self):
        self.ruudut = [[Shakkiruutu(AbsoluuttinenKoordinaatti(x, y), None)
                        for x in range(9)] for y in range(9)]


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
# print(a.ruudut)

print(AbsoluuttinenKoordinaatti(5, 5) + SuhteellinenKoordinaatti(1, 2))
