/**
 *  初期処理
 */
$(document).ready(() => {
	getCourseList();
})

/**
 *  対象講座一覧取得処理
 */
const getCourseList = () => {
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8080/WebFrontUI/courses',
		datatype: 'json',
		success: function(data){
			for (var i in data){
				var tr = $('<tr />');
				var course_id = $('<td>' + data[i].course_id + '</td>');
				var course_name = $('<td><a href="./play.html?user_id=1&course_id=1&movie_id=1">' + data[i].course_name + '</a></td>');
				tr.append(course_id);
				tr.append(course_name);

				$('#course-table-body').append(tr);
			}
		},
		error: function(){
			// エラー時の処理未実装
			alert("対象講座一覧の取得に失敗しました。");
		}
	})
}