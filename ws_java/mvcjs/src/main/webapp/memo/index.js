window.onload = () => {
  document.getElementById('btn_create').addEventListener('click', () => create());
  document.getElementById('add').addEventListener('keypress', (event) => {create_enter(event)});
}

const create = () => {
  let add_tag = document.getElementById('add');
  console.log('-> ', add_tag.value);

  const li = document.createElement('li');
  li.style.marginBottom = '5px';

  const div = document.createElement('div');    // 글 내용 + 삭제 버튼

  // 글내용
  const divtext = document.createElement("div"); // 글 내용
  divtext.textContent = add_tag.value;

  div.appendChild(divtext);     // div에 div 추가 : appendChild()

  // 삭제 버튼
  const button = document.createElement("button");
  button.textContent = "삭제";
  button.style.marginLeft = '10px';
  button.style.paddingTop = '0px';
  button.style.paddingBottom = '0px';
  button.style.height = '30px';
  button.setAttribute('class', 'btn btn-primary btn-sm');

  // 항목 삭제 이벤트
  button.addEventListener('click', () => {
    // 현재 태그로부터 가장 가까운 부모 태그중에 li 태그를 찾음
    const delete_target = button.closest('li');
    console.log(delete_target);
    document.getElementById('memo_list').removeChild(delete_target);    // ul 태그에서 li 삭제 : removeChild()
  })
  
  div.appendChild(button);    // div에 button 추가 : : appendChild()

  li.appendChild(div);
  document.getElementById('memo_list').appendChild(li);

  add_tag.value = ''; // 기존 입력값 삭제
}

const create_enter = (event) => {
  if (event.key == 'Enter') {
    create();
  }
}