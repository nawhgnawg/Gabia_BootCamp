# list 형태로 전달된 1차원 배열의 합을 구하여 리턴하는 함수
# 배열의 합 산출
def calc(data):  # calc함수는 데이터를 전달 받는다.
    tot = 0  # tot 변수에 0을 할당한다.
    for su in data:  # su에 데이터를 넣는다.
        # print(su) # su를 출력한다.
        tot = tot + su

    return tot


# 원룸의 크기 계산
def oneroom(width, height):
    # print('->', width, height)
    jegob = width * height
    pysu = jegob / 3.3

    return jegob, pysu


# 체질량지수(BMI) = (몸무게(kg) / 신장의 제곱) * 10000
def calcbmi(weight, height):
    bmi = (weight / (height ** 2)) * 10000

    return bmi


# 은행 이자 계산
def calcija(wonkum):
    ija = int(wonkum * 0.02)
    mon = int(ija / 12)
    day = int(ija / 365)
    receive = int(day - day * 0.14)

    return ija, mon, day, receive


# 다중 반환
def calc2(num1, num2):
    sum = num1 + num2
    sub = num1 - num2
    mul = num1 * num2
    div = round(num1 / num2, 1)
    mok = num1 // num2
    nam = num1 % num2

    return sum, sub, mul, div, mok, nam


# 성적 계산
def score(python, java, algorithm):
    total = python + java + algorithm
    avg = total / 3
    ox = ""
    if avg >= 60:
      ox = "통과"
    else:
      ox = "다음에 응시해주세요"
    return python, java, algorithm, avg, ox


def calcGrade(score):
  grade = ""
  if score >= 90:
    grade = "A"
  elif score >= 80:
    grade = "B"
  elif score >= 70:
    grade = "C"
  elif score >= 60:
    grade = "D"
  elif score >= 0:
    grade = "F"
  else:
    print("음수는 처리하지 않습니다.")
    grade = "없음"

  return grade

# 테스트
# calc함수를 실행하고 나서 합계를 리턴받아 hap 변수에 저장한다.
# hap = calc([1,2,3]) # 배열 값 1, 2, 3을 calc 함수로 전달한다. calc 함수를 호출한다. calc함수 실행
# print(hap)

# print(f'__name__ 시스템 변수: {__name__}') # __name__ 시스템 변수
# __name__: __main__  <- python Core.py 직접 실행
# __name__: Core      <- CoreTest.ipynb import
if __name__ == '__main__':  # python Core.py
    hap = calc([1, 2, 3])  # 배열 값 1, 2, 3을 calc 함수로 전달한다. calc 함수를 호출한다. calc함수 실행
    print(hap)
