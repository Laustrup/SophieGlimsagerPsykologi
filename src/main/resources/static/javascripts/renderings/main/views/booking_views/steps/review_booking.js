function renderBookingReview() {
    document.getElementById("booking_frame").innerHTML = `
        <div>
            <div>
                
            </div>
            <button class="positive_button">Book</button>
        </div>
        ${bookingNavigationButtons()}
    `;
}