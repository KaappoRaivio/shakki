class Shakkinappula:
    """Älä instantioi tästä!!!"""

    def __init__(self, allowed_squares, sijainti):
        self.relative_allowed_squares = allowed_squares

        if isinstance(sijainti, AbsoluuttinenKoordinaatti):
            self.sijainti = sijainti
        else:
            raise TypeError("Invalid type of the coordinate!")

    def move(self, suhteellinen_koordinaatti):
        pass


    @property
    def pos_x(self):
        return self.sijainti.x

    @property
    def pos_y(self):
        return self.sijainti.y
