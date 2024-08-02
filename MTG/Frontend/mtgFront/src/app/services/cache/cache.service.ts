import { Injectable } from '@angular/core';

interface CacheEntry {
  data: any;
  expiry: number;
}

@Injectable({
  providedIn: 'root',
})
export class CacheService {
  private cache: Map<string, CacheEntry> = new Map();
  public DEFAULT_CACHE_TIME = 5 * 60 * 1000;

  constructor() {}

  put(key: string, data: any, expiresIn: number = this.DEFAULT_CACHE_TIME) {
    const expiry = Date.now() + expiresIn;
    this.cache.set(key, { data, expiry });
    console.log(`Cache set: ${key}`);
  }

  get(key: string): any | null {
    const entry = this.cache.get(key);
    if (!entry) {
      console.log(`Cache miss: ${key}`);
      return null;
    }

    if (Date.now() > entry.expiry) {
      console.log(`Cache expired: ${key}`);
      this.cache.delete(key);
      return null;
    }
  }

  clear() {
    this.cache.clear();
    console.log('Cache cleared');
  }

  generateCacheKey(url: string, params: { [key: string]: string }): string {
    const sortedParams = Object.keys(params)
      .sort()
      .map((key) => `${key}=${params[key]}`)
      .join('&');

    return `${url}?${sortedParams}`;
  }
}
