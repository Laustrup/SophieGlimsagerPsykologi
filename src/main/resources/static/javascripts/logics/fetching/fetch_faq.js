async function insertFAQ() {
    return await fetchElement({
        url: FAQURL,
        method: POST
    });
}

async function getFAQ() {
    return await fetchElement({
        url: FAQURL,
        method: GET
    })
}

async function deleteFAQ() {
    return await fetchElement({
        url: FAQURL,
        method: DELETE
    });
}