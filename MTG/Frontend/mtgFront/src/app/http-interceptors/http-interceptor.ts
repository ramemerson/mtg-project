import {
  HttpEvent,
  HttpHandlerFn,
  HttpRequest,
  HttpResponse,
  HttpInterceptorFn,
} from '@angular/common/http';
import { inject } from '@angular/core';
import { Observable, of, tap } from 'rxjs';
import { CacheService } from '../services/cache/cache.service';

export const cachingInterceptor: HttpInterceptorFn = (
  req: HttpRequest<unknown>,
  next: HttpHandlerFn
) => {
  console.log('Interceptor called for url:', req.url);

  const cacheService = inject(CacheService);

  const cacheableUrls: string[] = [
    'http://localhost:8080/',
    'https://api.scryfall.com/cards/',
    'https://cards.scryfall.io/small/front/',
    'https://cards.scryfall.io/normal/front/',
  ];

  const cacheExpirations: { [key: string]: number } = {
    'http://localhost:8080/': 30 * 60 * 1000,
    'https://api.scryfall.com/cards/': 30 * 60 * 1000,
    'https://cards.scryfall.io/small/front/': 30 * 60 * 1000,
    'https://cards.scryfall.io/normal/front/': 30 * 60 * 1000,
  };

  // Only cache GET requests
  if (req.method !== 'GET') {
    return next(req);
  }

  // Check if the URL is cacheable
  if (!isCacheable(req.url)) {
    console.log('Getting data from cache');
    return next(req);
  }

  // Generate cache key including query params
  const cacheKey = cacheService.generateCacheKey(
    req.url,
    req.params.keys().reduce((acc, key) => {
      acc[key] = req.params.get(key) || '';
      return acc;
    }, {} as { [key: string]: string })
  );

  // Check for force refresh header
  if (req.headers.get('X-Refresh')) {
    cacheService.clear();
    return sendRequest(req, next, cacheKey);
  }

  // Check cache
  const cachedResponse = cacheService.get(cacheKey);
  if (cachedResponse) {
    return of(new HttpResponse({ body: cachedResponse }));
  }

  // If not in cache, send request and cache response
  return sendRequest(req, next, cacheKey);

  function sendRequest(
    req: HttpRequest<unknown>,
    next: HttpHandlerFn,
    cacheKey: string
  ): Observable<HttpEvent<unknown>> {
    return next(req).pipe(
      tap((event: HttpEvent<unknown>) => {
        if (event instanceof HttpResponse) {
          cacheService.put(cacheKey, event.body, getCacheExpiration(req.url));
        }
      })
    );
  }

  function isCacheable(url: string): boolean {
    console.log(url);
    return cacheableUrls.some((cacheableUrl) => url.includes(cacheableUrl));
  }

  function getCacheExpiration(url: string): number {
    return cacheExpirations[url] || cacheService.DEFAULT_CACHE_TIME;
  }
};
