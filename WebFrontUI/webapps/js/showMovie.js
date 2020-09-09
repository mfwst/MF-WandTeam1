/**
 *  初期処理
 */
$(document).ready(() => {
  // クエリパラメータの取得
  const queryParamer = convertQueryToArray(location.search);

  // 学習履歴情報をセット
  const movieId = queryParamer.movie_id
  document.getElementById('userId').innerHTML = queryParamer.user_id;
  document.getElementById('courseId').innerHTML = queryParamer.course_id;
  document.getElementById('nowPlay').innerHTML = movieId;

  // 初期動画をセット
  getResource('movies', 'movie_id', movieId).done((json) =>{
    document.querySelector("#v source").src = json[0].movie_url
    document.querySelector("#v").load();
  });

  // 動画再ロードのイベントリスナーを起動
  listenLoadStart();
})


/**
 *  動画の再ロードのイベントリスナー
 */
const listenLoadStart = () => {
  const video = document.getElementById('v');

  // 動画ロード時に実行
  video.addEventListener("loadstart", () => {
    const nowPlay = document.getElementById('nowPlay').innerHTML

    // 再生する動画データを取得する
    getResource('movies', 'movie_id', nowPlay).done((json) =>{
      
      // 選択肢表示秒数を取得する
      const second = json[0].movie_second
      
      // 次の情報を取得する
      getResource('choices', 'movie_id', nowPlay).done((json) => {
        
        // 動画再生時間のイベントリスナーを起動させる
        listenTimeUpdate(json, second);
      
      })
    })
  })
} 


/**
 *  動画再生時間のイベントリスナー
 */
const listenTimeUpdate = (json, second) => {
  const video = document.getElementById('v');

  // 動画再生時間変更時に実行
  video.addEventListener("timeupdate", () => {
  
  // 選択肢表示秒数以上で選択肢を表示する
  if(video.currentTime < second){
    document.getElementById('text').innerHTML = ""
  } else {
    let ul = ``
    let list = ``;

    for (let index in json) {
      const choice = json[index].choice_id
      const nextPlay = json[index].next_movie;
      const text = json[index].choice_name;

      // value:選択肢番号   id:次の動画ID   text:表示テキスト
      let listTemplate = `<li class='choice' value='${choice}' id='${nextPlay}'>${text}</li>`
      list += listTemplate;
    }

    // 選択肢の埋め込み
    ul = `<ul>${list}</ul>`
    document.getElementById('text').innerHTML = ul;
  }
  })
}


/**
 *  ボタン押下時に動画を切り替える
 */ 
$(document).on('click', '.choice', (event) => {

  // 現在の学習動画情報を取得
  const userId = document.getElementById('userId').innerHTML
  const courseId = document.getElementById('courseId').innerHTML
  const nowPlay = document.getElementById('nowPlay').innerHTML

  // JSON作成
  let requestBody = {}
  requestBody.user_id = userId;
  requestBody.course_id = courseId;
  requestBody.movie_id = nowPlay;

  insertResource('histories', requestBody).done(() => {
    //TODO POST後処理未実装
  })

  // 次の動画IDの取得
  const nextPlay = event.target.id;

  // 次の動画の再生
  getResource('movies', 'movie_id', nextPlay).done((json) => {
    document.querySelector("#v source").src = json[0].movie_url
    document.getElementById('nowPlay').innerHTML = json[0].movie_id
    document.querySelector("#v").load();
  })
});


/**
 *  リクエストパラメータの連想配列化
 */ 
const convertQueryToArray = (queryParamer) =>{
  // '?' を除去する
  let query = queryParamer.substring(1);

  // '&' で分割する
  let parameters = query.split('&');

  let queryObj = new Object();
  for (let i = 0; i < parameters.length; i++) {
    // '=' で分割する
    const element = parameters[i].split('=');

    // パラメータ名をキーとして連想配列に追加する
    const key = decodeURIComponent(element[0]);
    const value = decodeURIComponent(element[1]); 
    queryObj[key] = value.replace(/'/g, "");
  }

  return queryObj;
}


/**
 *  REST API GET通信
 */ 
const getResource = (resource, query = null, parameter = null) => {
  let endpoint = `http://localhost:8080/WebFrontUI/${resource}`;
  let queryParameter = `?${query}=${parameter}`

  if (parameter != null){
    endpoint += queryParameter;
  }

  return $.ajax({
    type: "GET",
    url: endpoint,
    dataType: "json"
  })
}

/**
 *  REST API POST通信
 */ 
const insertResource = (resource, requestBody) => {
  let endpoint = `http://localhost:8080/WebFrontUI/${resource}`;

  return $.ajax({
    type: "POST",
    url: endpoint,
    data: JSON.stringify(requestBody)
  })
}