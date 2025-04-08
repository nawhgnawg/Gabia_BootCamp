console.log('import complete');

// let title = document.querySelector('#title').textContent;
let title = document.getElementById('title').textContent;
console.log(title);   // DOM 테스트

let tagName = document.getElementById('title').tagName
console.log(tagName); // H1

// let ct1 

let ct2 = document.querySelectorAll('.container');
console.log(ct2);                   // NodeList(3) [div.container, div.container, div.container]
console.log(ct2.textContent);       // undefined
console.log(ct2.length);            // 3

console.log(ct2[0].textContent);    // DOM 테스트
console.log(ct2[1].textContent);    // 영역 1입니다.
console.log(ct2[2].textContent);    // 영역 2입니다.

let img = document.createElement("img");
img.src = './images/조인성01.png';
// img.width = 180;
// img.height = 130;
img.style.margin = '10px';
img.style.width = '20%';
// img.style.height = '10%';    // 부모 태그가 px 선언

// 부모 태그의 가장 앞쪽에 붙힌다.
ct2[1].prepend(img);

img = document.createElement("img");
img.src = "./images/이유리01.jpg";
// img.width = 180;  // O
// img.width = "50%"; // X
img.style.width="20%"; // O
// img.height = 130;
img.style.margin = '10px'; // CSS

ct2[1].prepend(img); // 부모 태그의 가장 앞에 추가

let div1 = document.createElement("div");

img = document.createElement("img");
img.src = "./images/정우01.jpg";
img.width = 180;  // O
img.height = 130;
img.style.margin = '10px'; // CSS

div1.appendChild(img);

const body = document.querySelector("body");
body.appendChild(div1);

// button 생성
let btn = document.createElement("button");
btn.textContent = '트리 제작';
btn.style.marginTop = '10px';
btn.style.marginLeft = '100px';
btn.setAttribute("class", "btn btn-primary btn-sm");    // 속성명, 부트스트랩

// 이벤트 등록
btn.addEventListener("click", () => {
  ct2[2].textContent = "10월 마지막주 토요일 트리 제작";
})

body.appendChild(btn);

btn = document.createElement("button");
btn.textContent = '기본값';
btn.style.marginTop = '10px';
btn.style.marginLeft = '10px';
btn.setAttribute("class", "btn btn-primary btn-sm");

btn.addEventListener("click", () => {
  ct2[2].textContent = "영역 2입니다.";
})

body.appendChild(btn);
