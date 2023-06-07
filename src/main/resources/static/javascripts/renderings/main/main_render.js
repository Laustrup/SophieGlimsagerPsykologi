render().then();

async function render() {
    renderHead();
    await renderHeader();
    renderFooter();
}