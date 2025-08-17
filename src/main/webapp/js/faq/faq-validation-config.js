/**
 * 
 */
$(function () {
	$('#addForm').validate({
	    rules: {                    // 유효성 검사 규칙
	        title: {                
	            required: true,     // 필수 입력
	            minlength : 2       // 최소 입력 길이
	        },
	        content: {                  
	            required: true,     // 필수 입력
	            minlength : 2       // 최소 입력 길이
	        }
	    },
	    messages: {                 // 오류값 발생시 출력할 메시지 수동 지정
	        title: {
	            required: '필수 입력 항목입니다.',
	            minlength: '최소 {0}글자 이상 입력하세요.'
	        },
	        content: {
	            required: '필수 입력 항목입니다.',
	            minlength: '최소 {0}글자 이상 입력하세요.'
	        }
	    }
	});
})