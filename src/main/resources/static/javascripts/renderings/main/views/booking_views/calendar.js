let week = [];

function renderCalendar(bookings) {
    const now = new Date();
    const weekOfMonth = (0 | now.getDate() / 7)+1;

    for (let i = 1; i <= 7; i++) {
        const date = new Date();
        date.setDate(new Date().getDate() + (i - new Date().getDate()) + (weekOfMonth*7) - 3);
        week.push(date);
    }

    let html = `<div></div>`;

    for (let i = 0; i < 7; i++)
        html += `<div class="date_column"><p class="week_calendar_title">${week[i].toLocaleString("da-DK", {
            weekday: "long"
        }) + "<br>" + week[i].toLocaleString("da-DK", {
            month: "long",
            day: "numeric"
        })}</p></div>`;

    for (let i = 8; i < 21; i++) {
        html += `<div class="hour_row"><p>${i}:00-${i+1}:00</p></div>`;

        for (let j = 1; j <= 7; j++) {
            // booking = bookings.filter(booking => booking.start === week[i]);
            html += `<div class="calendar_field ${i + ":00-" + i + "1:00" + "_week_" + j}"></div>`;
            // if (booking.length > 0)
            //     html += `<button class="ui-widget-overlay"></button>`
        }
    }

    document.getElementById("calendar_frame").innerHTML = html;
}

function displayWeek(date) {
    let dayNum = date.getUTCDay() || 7;
    date.setUTCDate(date.getUTCDate() + 4 - dayNum);
    let yearStart = new Date(Date.UTC(date.getUTCFullYear(),0,1));
    return Math.ceil((((date - yearStart) / 86400000) + 1)/7);
}