import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ThemeService {
  private lightTheme = 'aura-light-amber';
  private darkTheme = 'aura-dark-amber';

  constructor() {}

  switchTheme(isDarkMode: boolean): void {
    const theme = isDarkMode ? this.lightTheme : this.darkTheme;
    const themeLink = document.getElementById('app-theme') as HTMLLinkElement;

    if (themeLink) {
      console.log(`Switching to theme: assets/themes/${theme}/theme.css`);
      themeLink.href = `assets/themes/${theme}/theme.css`; // Update the path dynamically
    }
  }
}
