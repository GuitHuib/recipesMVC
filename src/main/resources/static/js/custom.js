$(".item").each(function() {
    $(this).on("click", handleClick);
});

function handleClick(e) {
    $(this).toggleClass("checked");
}