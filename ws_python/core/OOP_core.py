class Calc:
    def __init__(self, su1, su2): # 생성자
        self.su1 = su1
        self.su2 = su2

    def add(self): # 함수, 메소드
        return self.su1 + self.su2
    
    def sub(self):
        return self.su1 - self.su2
     
class Calcroom:
    def __init__(self, width, height):
        self.width = width
        self.height = height

    def calcroom(self):
        jegob = self.width * self.height
        pysu = jegob / 3.3
   
        return jegob, pysu  
     
class Calcbmi :
    def __init__(self, weight, height):
        self.weight = weight
        self.height = height
   
    def calcbmi(self):
        bmi = (self.weight / (self.height **2)) * 10000
       
        return bmi
    
class Toss:
    def __init__(self,wonkum):
        self.wonkum = wonkum
        
    def calctoss(self):
        ija = int(self.wonkum * 0.02)
        mon = int(ija / 12)
        day = int(ija / 365)
        receive = int(day - day * 0.14)

        return ija, mon, day, receive