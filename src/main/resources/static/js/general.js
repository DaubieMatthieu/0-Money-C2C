$(document).ready(()=>{
    initDropdown();
    $('.scroll-down').each((index, elt)=>scrollDown(elt));
})

function initDropdown() {
    //custom dropdown opening and closing to avoid closing when user clicks on <select>
    $('button.dropdown-toggle').on('click', function () {
        $(this).parent().dropdown('toggle');
    });

    $('body').on('click', function (e) {
        if (!$('.dropdown').is(e.target)
            && $('.dropdown').has(e.target).length === 0
            && $('.show').has(e.target).length === 0
        ) {
            $(".dropdown").dropdown('hide');
        }
    });
}

function scrollDown(container) {
    //make scrollbar scroll down to bottom of the container
    container.scrollTop = container.scrollHeight;
}
